<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Message;
use App\Models\User;

class MessagePolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(User $user, Message $message): bool
    {
        return $user->id === $message->receiver->id || $user->id === $message->sender->id;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_MESSAGES->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Message $message): bool
    {
        if ($user->can(PermissionsEnum::UPDATE_MESSAGES->value)) {
            return $message->sender->id === $user->id;
        }
        return false;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Message $message): bool
    {
        if ($user->can(PermissionsEnum::DELETE_MESSAGES->value)) {
            return $message->sender->id === $user->id;
        }
        return false;
    }
}
