<?php

namespace App\Enums;

use App\Traits\EnumToArray;

enum ModerationEnum: string
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
}
