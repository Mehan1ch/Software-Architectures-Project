<?php

namespace Database\Seeders;

use App\Enums\RolesEnum;
use App\Models\User;
use Illuminate\Database\Seeder;

class TestSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {



        $this->call([
            PermissionSeeder::class,
            UserSeeder::class,
            LanguageSeeder::class,
            RatingSeeder::class,
            WorkSeeder::class,
            CategorySeeder::class,
            ChapterSeeder::class,
            CharacterSeeder::class,
            CollectionSeeder::class,
            CommentSeeder::class,
            LikeSeeder::class,
            MessageSeeder::class,
            TagSeeder::class,
            WarningSeeder::class,]);
    }
}
