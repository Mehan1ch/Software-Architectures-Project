<?php

namespace Database\Seeders;

use App\Enums\PermissionsEnum;
use App\Enums\RolesEnum;
use App\Models\Permission;
use App\Models\Role;
use Illuminate\Database\Seeder;
use Spatie\Permission\PermissionRegistrar;

class PermissionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        // Reset cached roles and permissions
        app()[PermissionRegistrar::class]->forgetCachedPermissions();

        //Create roles
        $admin = Role::create(['name' => RolesEnum::ADMIN->value]);
        $registered = Role::create(['name' => RolesEnum::REGISTERED->value]);
        $moderator = Role::create(['name' => RolesEnum::MODERATOR->value]);

        // Create permissions

        // Categories Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_CATEGORIES->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_CATEGORIES->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_CATEGORIES->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_CATEGORIES->value]);

        // Chapters Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_CHAPTERS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_CHAPTERS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_CHAPTERS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_CHAPTERS->value]);

        // Characters Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_CHARACTERS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_CHARACTERS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_CHARACTERS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_CHARACTERS->value]);

        // Collections Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_COLLECTIONS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_COLLECTIONS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_COLLECTIONS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_COLLECTIONS->value]);

        // Comments Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_COMMENTS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_COMMENTS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_COMMENTS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_COMMENTS->value]);

        // Languages Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_LANGUAGES->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_LANGUAGES->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_LANGUAGES->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_LANGUAGES->value]);

        // Likes Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_LIKES->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_LIKES->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_LIKES->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_LIKES->value]);

        // Messages Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_MESSAGES->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_MESSAGES->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_MESSAGES->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_MESSAGES->value]);

        // Ratings Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_RATINGS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_RATINGS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_RATINGS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_RATINGS->value]);

        // Tags Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_TAGS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_TAGS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_TAGS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_TAGS->value]);

        //User Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_USERS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_USERS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_USERS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_USERS->value]);

        // Warnings Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_WARNINGS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_WARNINGS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_WARNINGS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_WARNINGS->value]);

        // Work Permissions

        Permission::create(['name' => PermissionsEnum::VIEW_WORKS->value]);
        Permission::create(['name' => PermissionsEnum::CREATE_WORKS->value]);
        Permission::create(['name' => PermissionsEnum::UPDATE_WORKS->value]);
        Permission::create(['name' => PermissionsEnum::DELETE_WORKS->value]);

        // Moderation and admin related permissions

        Permission::create(['name' => PermissionsEnum::ACCESS_ADMIN_PANEL->value]);
        Permission::create(['name' => PermissionsEnum::MODERATE_WORKS->value]);

        // Assign permissions to roles

        $registered->givePermissionTo([
            // View
            PermissionsEnum::VIEW_CATEGORIES->value,
            PermissionsEnum::VIEW_CHAPTERS->value,
            PermissionsEnum::VIEW_CHARACTERS->value,
            PermissionsEnum::VIEW_COLLECTIONS->value,
            PermissionsEnum::VIEW_COMMENTS->value,
            PermissionsEnum::VIEW_LANGUAGES->value,
            PermissionsEnum::VIEW_LIKES->value,
            PermissionsEnum::VIEW_MESSAGES->value,
            PermissionsEnum::VIEW_RATINGS->value,
            PermissionsEnum::VIEW_TAGS->value,
            PermissionsEnum::VIEW_USERS->value,
            PermissionsEnum::VIEW_WARNINGS->value,
            PermissionsEnum::VIEW_WORKS->value,
            // Create
            PermissionsEnum::CREATE_CHAPTERS->value,
            PermissionsEnum::CREATE_CHARACTERS->value,
            PermissionsEnum::CREATE_COLLECTIONS->value,
            PermissionsEnum::CREATE_COMMENTS->value,
            PermissionsEnum::CREATE_LIKES->value,
            PermissionsEnum::CREATE_MESSAGES->value,
            PermissionsEnum::CREATE_TAGS->value,
            PermissionsEnum::CREATE_WORKS->value,
            // Update
            PermissionsEnum::UPDATE_CHAPTERS->value,
            PermissionsEnum::UPDATE_CHARACTERS->value,
            PermissionsEnum::UPDATE_COLLECTIONS->value,
            PermissionsEnum::UPDATE_COMMENTS->value,
            PermissionsEnum::UPDATE_LIKES->value,
            PermissionsEnum::UPDATE_MESSAGES->value,
            PermissionsEnum::UPDATE_TAGS->value,
            PermissionsEnum::UPDATE_WORKS->value,
            // Delete
            PermissionsEnum::DELETE_CHAPTERS->value,
            PermissionsEnum::DELETE_CHARACTERS->value,
            PermissionsEnum::DELETE_COLLECTIONS->value,
            PermissionsEnum::DELETE_COMMENTS->value,
            PermissionsEnum::DELETE_LIKES->value,
            PermissionsEnum::DELETE_MESSAGES->value,
            PermissionsEnum::DELETE_TAGS->value,
            PermissionsEnum::DELETE_WORKS->value,
        ]);

        $moderator->givePermissionTo([
            PermissionsEnum::VIEW_CATEGORIES->value,
            PermissionsEnum::CREATE_CATEGORIES->value,
            PermissionsEnum::UPDATE_CATEGORIES->value,
            PermissionsEnum::DELETE_CATEGORIES->value,
            PermissionsEnum::VIEW_CHAPTERS->value,
            PermissionsEnum::CREATE_CHAPTERS->value,
            PermissionsEnum::UPDATE_CHAPTERS->value,
            PermissionsEnum::DELETE_CHAPTERS->value,
            PermissionsEnum::VIEW_CHARACTERS->value,
            PermissionsEnum::CREATE_CHARACTERS->value,
            PermissionsEnum::UPDATE_CHARACTERS->value,
            PermissionsEnum::DELETE_CHARACTERS->value,
            PermissionsEnum::VIEW_COLLECTIONS->value,
            PermissionsEnum::CREATE_COLLECTIONS->value,
            PermissionsEnum::UPDATE_COLLECTIONS->value,
            PermissionsEnum::DELETE_COLLECTIONS->value,
            PermissionsEnum::VIEW_COMMENTS->value,
            PermissionsEnum::CREATE_COMMENTS->value,
            PermissionsEnum::UPDATE_COMMENTS->value,
            PermissionsEnum::DELETE_COMMENTS->value,
            PermissionsEnum::VIEW_LANGUAGES->value,
            PermissionsEnum::CREATE_LANGUAGES->value,
            PermissionsEnum::UPDATE_LANGUAGES->value,
            PermissionsEnum::DELETE_LANGUAGES->value,
            PermissionsEnum::VIEW_LIKES->value,
            PermissionsEnum::CREATE_LIKES->value,
            PermissionsEnum::UPDATE_LIKES->value,
            PermissionsEnum::DELETE_LIKES->value,
            PermissionsEnum::VIEW_MESSAGES->value,
            PermissionsEnum::CREATE_MESSAGES->value,
            PermissionsEnum::UPDATE_MESSAGES->value,
            PermissionsEnum::DELETE_MESSAGES->value,
            PermissionsEnum::VIEW_RATINGS->value,
            PermissionsEnum::CREATE_RATINGS->value,
            PermissionsEnum::UPDATE_RATINGS->value,
            PermissionsEnum::DELETE_RATINGS->value,
            PermissionsEnum::VIEW_TAGS->value,
            PermissionsEnum::CREATE_TAGS->value,
            PermissionsEnum::UPDATE_TAGS->value,
            PermissionsEnum::DELETE_TAGS->value,
            PermissionsEnum::VIEW_WARNINGS->value,
            PermissionsEnum::CREATE_WARNINGS->value,
            PermissionsEnum::UPDATE_WARNINGS->value,
            PermissionsEnum::DELETE_WARNINGS->value,
            PermissionsEnum::VIEW_WORKS->value,
            PermissionsEnum::CREATE_WORKS->value,
            PermissionsEnum::UPDATE_WORKS->value,
            PermissionsEnum::DELETE_WORKS->value,
            PermissionsEnum::ACCESS_ADMIN_PANEL->value,
            PermissionsEnum::MODERATE_WORKS->value,
        ]);
    }
}
