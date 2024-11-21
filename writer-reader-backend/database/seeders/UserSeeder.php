<?php

namespace Database\Seeders;

use App\Enums\RolesEnum;
use App\Models\User;
use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        // Users for testing purposes
        User::factory()->create([
            'name' => 'Admin Pál',
            'email' => 'admin@test.com',
            'password' => bcrypt('asdasd'),
        ])->each(function ($user) {
            $user->assignRole(RolesEnum::ADMIN->value);
        });

        User::factory()->create([
            'name' => 'Moderátor Pál',
            'email' => 'moderator@test.com',
            'password' => bcrypt('asdasd'),
        ])->each(function ($user) {
            $user->assignRole(RolesEnum::MODERATOR->value);
        });

        User::factory()->create([
            'name' => 'Regisztrált Pál',
            'email' => 'registered@test.com',
            'password' => bcrypt('asdasd'),
        ])->each(function ($user) {
            $user->assignRole(RolesEnum::REGISTERED->value);
        });

        User::factory(20)->create()->each(function ($user) {
            $user->assignRole(RolesEnum::REGISTERED->value);
        });
    }
}
