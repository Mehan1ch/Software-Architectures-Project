<?php

namespace Database\Seeders;

use App\Models\Collection;
use App\Models\Message;
use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Database\Factories\WorkFactory;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $this->call([
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
            WarningSeeder::class,
        ]);
    }
}
