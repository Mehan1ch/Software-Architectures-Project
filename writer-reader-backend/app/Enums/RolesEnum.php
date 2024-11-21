<?php

namespace App\Enums;

use App\Traits\EnumToArray;

enum RolesEnum: string
{
    use EnumToArray;
    case REGISTERED = 'registered';
    case MODERATOR = 'moderator';
    case ADMIN = 'admin';
}
