<?php

namespace App\Filament\Resources\WorkResource\Pages;

use App\Enums\ModerationEnum;
use App\Enums\PermissionsEnum;
use App\Filament\Resources\WorkResource;
use App\Models\Work;
use Filament\Actions;
use Filament\Notifications\Notification;
use Filament\Resources\Pages\ViewRecord;

class ViewWork extends ViewRecord
{
    protected static string $resource = WorkResource::class;

    protected function getHeaderActions(): array
    {
        return [
            Actions\EditAction::make(),
            Actions\Action::make('approve')
                ->label('Approve')
                ->color('success')
                ->icon('heroicon-o-check')
                ->visible(fn($record) => $record->moderation_status->ableToTransferTo(ModerationEnum::APPROVED) && auth()->user()->can(PermissionsEnum::MODERATE_WORKS->value))
                ->action(function ($record) {
                    $work = Work::query()->where('id', $record->id)->first();
                    $work->update([
                        'moderation_status' => ModerationEnum::APPROVED,
                    ]);
                    $this->refreshFormData([
                        'moderation_status'
                    ]);
                    Notification::make()
                        ->title('Approved successfully')
                        ->success()
                        ->send();
                    $this->redirect($record->id);
                })
                ->authorize(PermissionsEnum::MODERATE_WORKS->value),
            Actions\Action::make('Request Edit')
                ->label('Request Edit')
                ->color('danger')
                ->icon('heroicon-o-x-mark')
                ->visible(fn($record) => $record->moderation_status->ableToTransferTo(ModerationEnum::EDIT_REQUESTED) && auth()->user()->can(PermissionsEnum::MODERATE_WORKS->value))
                ->action(function ($record) {
                    $work = Work::query()->where('id', $record->id)->first();
                    $work->update([
                        'moderation_status' => ModerationEnum::EDIT_REQUESTED,
                    ]);
                    $this->refreshFormData([
                        'moderation_status'
                    ]);
                    Notification::make()
                        ->title('Edit requested successfully')
                        ->success()
                        ->send();
                    $this->redirect($record->id);
                })
                ->authorize(PermissionsEnum::MODERATE_WORKS->value),
        ];
    }
}
