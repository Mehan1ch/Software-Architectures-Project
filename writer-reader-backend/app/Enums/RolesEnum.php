<?php

namespace App\Enums;

enum RolesEnum: string
{
    case REGISTERED = 'registered';
    case MODERATOR = 'moderator';
    case ADMIN = 'admin';
}
