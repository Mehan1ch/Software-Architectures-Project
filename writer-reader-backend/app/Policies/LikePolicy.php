<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Like;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class LikePolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(?User $user, ?Like $like): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_LIKES->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Like $like): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_LIKES->value)) {
            return $like->user->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Like $like): bool
    {
        if ($user->can(PermissionsEnum::DELETE_LIKES->value)) {
            return $like->user->id === $user->id;
        }
        return false;
    }
}
