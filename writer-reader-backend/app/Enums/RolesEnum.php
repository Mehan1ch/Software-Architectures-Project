<?php

namespace App\Enums;

use App\Traits\EnumToArray;
use Filament\Support\Contracts\HasDescription;
use Filament\Support\Contracts\HasIcon;
use Filament\Support\Contracts\HasLabel;

enum RolesEnum: string implements HasIcon, HasLabel, HasDescription
{
    use EnumToArray;

    case REGISTERED = 'registered';
    case MODERATOR = 'moderator';
    case ADMIN = 'admin';

    public function getDescription(): ?string
    {
        return match ($this) {
            self::REGISTERED => 'The user is a registered user',
            self::MODERATOR => 'The user is a moderator',
            self::ADMIN => 'The user is an admin',
        };
    }

    public function getIcon(): ?string
    {
        return match ($this) {
            self::REGISTERED => 'heroicon-o-user',
            self::MODERATOR => 'heroicon-o-shield-exclamation',
            self::ADMIN => 'heroicon-o-shield-check',
        };
    }

    public function getLabel(): ?string
    {
        return match ($this) {
            self::REGISTERED => 'Registered',
            self::MODERATOR => 'Moderator',
            self::ADMIN => 'Admin',
        };
    }
}
