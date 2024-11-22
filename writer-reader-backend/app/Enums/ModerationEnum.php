<?php

namespace App\Enums;

use App\Traits\EnumToArray;
use Filament\Support\Contracts\HasDescription;
use Filament\Support\Contracts\HasIcon;
use Filament\Support\Contracts\HasLabel;

enum ModerationEnum: string implements HasLabel, HasIcon, HasDescription
{
    use EnumToArray;

    case PENDING = 'pending';
    case EDIT_REQUESTED = 'edit_requested';
    case APPROVED = 'approved';

    public function stateGraph(): array
    {
        return match ($this) {
            self::PENDING => [self::EDIT_REQUESTED, self::APPROVED],
            self::EDIT_REQUESTED, self::APPROVED => [self::PENDING],
        };
    }

    public function ableToTransferTo(self $new): bool
    {
        return in_array($new, $this->stateGraph());
    }

    public function getLabel(): ?string
    {
        return match ($this) {
            self::PENDING => 'Pending',
            self::EDIT_REQUESTED => 'Edit Requested',
            self::APPROVED => 'Approved',
        };
    }

    public function getIcon(): ?string
    {
        return match ($this) {
            self::PENDING => 'heroicon-o-question-mark-circle',
            self::EDIT_REQUESTED => 'heroicon-o-exclamation-circle',
            self::APPROVED => 'heroicon-o-check-circle',
        };
    }

    public function getDescription(): ?string
    {
        return match ($this) {
            self::PENDING => 'The work is pending moderation.',
            self::EDIT_REQUESTED => 'The work has been requested for edits.',
            self::APPROVED => 'The work has been approved.',
        };
    }
}
