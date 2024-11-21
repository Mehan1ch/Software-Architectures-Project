<?php

namespace App\Filament\Resources;

use App\Enums\ModerationEnum;
use App\Enums\PermissionsEnum;
use App\Enums\RolesEnum;
use App\Filament\Resources\WorkResource\Pages;
use App\Filament\Resources\WorkResource\RelationManagers;
use App\Models\Rating;
use App\Models\Work;
use Filament\Forms;
use Filament\Forms\Form;
use Filament\Notifications\Notification;
use Filament\Resources\Resource;
use Filament\Tables;
use Filament\Tables\Table;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletingScope;
use Illuminate\Support\Facades\Auth;

class WorkResource extends Resource
{
    protected static ?string $model = Work::class;

    protected static ?string $navigationIcon = 'heroicon-o-book-open';

    public static function form(Form $form): Form
    {
        return $form
            ->schema([
                Forms\Components\TextInput::make('title')
                    ->required()
                    ->maxLength(255),
                Forms\Components\MarkdownEditor::make('content')
                    ->required()
                    ->columnSpanFull(),
                Forms\Components\Select::make('user_id')
                    ->label('Creator')
                    ->searchable()
                    ->relationship('creator', 'name')
                    ->required(),
                Forms\Components\Select::make('moderation_status')
                    ->searchable()
                    ->options(ModerationEnum::class)
                    ->required(),
                Forms\Components\Select::make('moderator_id')
                    ->searchable()
                    ->relationship('moderator', 'name'),
                Forms\Components\Select::make('rating_id')
                    ->searchable()
                    ->relationship('rating', 'details'),
                Forms\Components\Select::make('language_id')
                    ->searchable()
                    ->relationship('language', 'name'),
            ]);
    }

    public static function table(Table $table): Table
    {
        return $table
            ->columns([
                Tables\Columns\TextColumn::make('id')
                    ->label('ID')
                    ->searchable(),
                Tables\Columns\TextColumn::make('title')
                    ->sortable()
                    ->searchable(),
                Tables\Columns\TextColumn::make('creator.name')
                    ->sortable()
                    ->searchable(),
                Tables\Columns\TextColumn::make('moderation_status')
                    ->searchable()
                    ->sortable(),
                Tables\Columns\TextColumn::make('moderator.name')
                    ->sortable()
                    ->searchable(),
                Tables\Columns\TextColumn::make('rating.details')
                    ->sortable()
                    ->searchable(),
                Tables\Columns\TextColumn::make('language.name')
                    ->sortable()
                    ->searchable(),
                Tables\Columns\TextColumn::make('created_at')
                    ->dateTime()
                    ->sortable()
                    ->toggleable(isToggledHiddenByDefault: true),
                Tables\Columns\TextColumn::make('updated_at')
                    ->dateTime()
                    ->sortable()
                    ->toggleable(isToggledHiddenByDefault: true),
            ])
            ->filters([
                //
            ])
            ->actions([
                Tables\Actions\Action::make('approve')
                    ->label('Approve')
                    ->color('success')
                    ->icon('heroicon-o-check')
                    ->visible(fn($record) => $record->moderation_status->ableToTransferTo(ModerationEnum::APPROVED) && auth()->user()->can(PermissionsEnum::MODERATE_WORKS->value))
                    ->action(function ($record) {
                        $work = Work::query()->where('id', $record->id)->first();
                        $work->update([
                            'moderation_status' => ModerationEnum::APPROVED,
                        ]);
                        Notification::make()
                            ->title('Approved successfully')
                            ->success()
                            ->send();
                    })
                    ->authorize(PermissionsEnum::MODERATE_WORKS->value),
                Tables\Actions\Action::make('Request Edit')
                    ->label('Request Edit')
                    ->color('danger')
                    ->icon('heroicon-o-x-mark')
                    ->visible(fn($record) => $record->moderation_status->ableToTransferTo(ModerationEnum::EDIT_REQUESTED) && auth()->user()->can(PermissionsEnum::MODERATE_WORKS->value))
                    ->action(function ($record) {
                        $work = Work::query()->where('id', $record->id)->first();
                        $work->update([
                            'moderation_status' => ModerationEnum::EDIT_REQUESTED,
                        ]);
                        Notification::make()
                            ->title('Edit requested successfully')
                            ->success()
                            ->send();
                    })
                    ->authorize(PermissionsEnum::MODERATE_WORKS->value),


                Tables\Actions\ViewAction::make(),
                Tables\Actions\EditAction::make(),
            ])
            ->bulkActions([
                Tables\Actions\BulkActionGroup::make([
                    Tables\Actions\DeleteBulkAction::make(),
                ]),
            ]);
    }

    public static function getRelations(): array
    {
        return [
            //
        ];
    }

    public static function getPages(): array
    {
        return [
            'index' => Pages\ListWorks::route('/'),
            'create' => Pages\CreateWork::route('/create'),
            'view' => Pages\ViewWork::route('/{record}'),
            'edit' => Pages\EditWork::route('/{record}/edit'),
        ];
    }

    public static function canCreate(): bool
    {
        return Auth::user()->hasRole(RolesEnum::ADMIN->value);
    }

    public static function canEdit(Model $record): bool
    {
        return Auth::user()->hasRole(RolesEnum::ADMIN->value);
    }

    public static function canDelete(Model $record): bool
    {
        return Auth::user()->hasRole(RolesEnum::ADMIN->value);
    }
}
