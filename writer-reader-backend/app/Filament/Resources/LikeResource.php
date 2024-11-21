<?php

namespace App\Filament\Resources;

use App\Enums\RolesEnum;
use App\Filament\Resources\LikeResource\Pages;
use App\Filament\Resources\LikeResource\RelationManagers;
use App\Models\Like;
use Filament\Forms;
use Filament\Forms\Form;
use Filament\Resources\Resource;
use Filament\Tables;
use Filament\Tables\Table;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletingScope;
use Illuminate\Support\Facades\Auth;

class LikeResource extends Resource
{
    protected static ?string $model = Like::class;

    protected static ?string $navigationIcon = 'heroicon-o-hand-thumb-up';

    public static function form(Form $form): Form
    {
        return $form
            ->schema([
                Forms\Components\Select::make('user_id')
                    ->relationship('user', 'name')
                    ->searchable()
                    ->required(),
                Forms\Components\Select::make('likeable_type')
                    ->options([
                        'App\Models\Comment' => 'Comment',
                        'App\Models\Work' => 'Work',
                        'App\Models\Collection' => 'Collection'
                    ])
                    ->searchable()
                    ->live()
                    ->required(),
                Forms\Components\Select::make('likeable_id')
                    //->relationship('likeable', 'id')
                    ->options(function (Forms\Get $get) {
                        return $get('likeable_type')::all()->pluck('id')->toArray();
                    })
                    ->live()
                    ->searchable()
                    ->required()
            ]);
    }

    public static function table(Table $table): Table
    {
        return $table
            ->columns([
                Tables\Columns\TextColumn::make('id')
                    ->label('ID')
                    ->searchable(),
                Tables\Columns\TextColumn::make('user.name')
                    ->searchable(),
                Tables\Columns\TextColumn::make('likeable_type')
                    ->searchable(),
                Tables\Columns\TextColumn::make('likeable_id')
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
            'index' => Pages\ListLikes::route('/'),
            'create' => Pages\CreateLike::route('/create'),
            'view' => Pages\ViewLike::route('/{record}'),
            'edit' => Pages\EditLike::route('/{record}/edit'),
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
