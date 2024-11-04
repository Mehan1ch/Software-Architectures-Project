<?php

namespace App\Enums;

enum ModerationEnum: string
{
    case PENDING = 'pending';
    case EDIT_REQUESTED = 'edit_requested';
    case APPROVED = 'approved';
}
