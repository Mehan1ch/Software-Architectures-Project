<?php

namespace App\Policies;

use App\Enums\PermissionsEnum;
use App\Models\Language;
use App\Models\User;

class LanguagePolicy
{
    /**
     * Determine whether the user can view the model.
     */
    public function view(User $user, Language $language): bool
    {
        return true;
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        return $user->can(PermissionsEnum::CREATE_LANGUAGES->value);
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(User $user, Language $language): bool
    {
        return $user->can(PermissionsEnum::UPDATE_LANGUAGES->value);
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(User $user, Language $language): bool
    {
        return $user->can(PermissionsEnum::DELETE_LANGUAGES->value);
    }
}
