<?php

namespace App\Filament\Resources\WarningResource\Pages;

use App\Filament\Resources\WarningResource;
use Filament\Actions;
use Filament\Resources\Pages\ViewRecord;

class ViewWarning extends ViewRecord
{
    protected static string $resource = WarningResource::class;

    protected function getHeaderActions(): array
    {
        return [
            Actions\EditAction::make(),
        ];
    }
}
