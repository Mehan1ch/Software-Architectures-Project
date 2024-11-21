<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Collection;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class CollectionPolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(?User $user): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_COLLECTIONS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Collection $collection): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_COLLECTIONS->value)) {
            return $collection->user->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Collection $collection): bool
    {
        if ($user->can(PermissionsEnum::DELETE_COLLECTIONS->value)) {
            return $collection->user->id === $user->id;
        }
        return false;
    }
}
