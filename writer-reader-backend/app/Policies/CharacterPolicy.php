<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Character;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class CharacterPolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(User $user, Character $character): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_CHARACTERS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Character $character): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_CHARACTERS->value)) {
            return $character->user->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Character $character): bool
    {
        if ($user->can(PermissionsEnum::DELETE_CHARACTERS->value)) {
            return $character->user->id === $user->id;
        }
        return false;
    }
}
