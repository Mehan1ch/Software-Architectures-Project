<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\User;
use App\Models\Warning;
use Illuminate\Auth\Access\Response;

class WarningPolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(?User $user, ?Warning $warning): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_WARNINGS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Warning $warning): bool
    {
        return $user->can(PermissionsEnum::UPDATE_WARNINGS->value);
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Warning $warning): bool
    {
        return $user->can(PermissionsEnum::DELETE_WARNINGS->value);
    }
}
