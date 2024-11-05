<?php

namespace App\Enums;

use App\Traits\EnumToArray;

enum ModerationEnum: string
{
    use EnumToArray;

    case PENDING = 'pending';
    case EDIT_REQUESTED = 'edit_requested';
    case APPROVED = 'approved';
}
