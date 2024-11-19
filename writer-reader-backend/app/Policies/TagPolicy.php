<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Tag;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class TagPolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(?User $user, ?Tag $tag): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_TAGS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Tag $tag): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_TAGS->value)) {
            return $tag->user->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Tag $tag): bool
    {
        if ($user->can(PermissionsEnum::DELETE_TAGS->value)) {
            return $tag->user->id === $user->id;
        }
        return false;
    }
}
