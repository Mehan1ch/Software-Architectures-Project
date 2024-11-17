<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Rating;
use App\Models\User;

class RatingPolicy
{

    /**
     * Determine whether the user can view the model.
     */
    public function view(User $user, Rating $rating): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_RATINGS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Rating $rating): bool
    {
        return $user->can(PermissionsEnum::UPDATE_RATINGS->value);
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Rating $rating): bool
    {
        return $user->can(PermissionsEnum::DELETE_RATINGS->value);
    }
}
