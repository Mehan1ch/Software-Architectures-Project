<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Chapter;
use App\Models\User;
use Illuminate\Auth\Access\Response;

class ChapterPolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(?User $user, ?Chapter $chapter): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_CHAPTERS->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Chapter $chapter): bool
    {
        if($user->can(PermissionsEnum::UPDATE_CHAPTERS->value)){
            return $chapter->work->creator->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Chapter $chapter): bool
    {
       if($user->can(PermissionsEnum::DELETE_CHAPTERS->value)){
           return $chapter->work->creator->id === $user->id;
       }
       return false;
    }
}
