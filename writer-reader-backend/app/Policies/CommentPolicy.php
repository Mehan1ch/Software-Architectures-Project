<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Comment;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class CommentPolicy
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
        return $user->can(PermissionsEnum::CREATE_COMMENTS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Comment $comment): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_COMMENTS->value)) {
            return $comment->user->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Comment $comment): bool
    {
        if ($user->can(PermissionsEnum::DELETE_COMMENTS->value)) {
            return $comment->user->id === $user->id;
        }
        return false;
    }
}
