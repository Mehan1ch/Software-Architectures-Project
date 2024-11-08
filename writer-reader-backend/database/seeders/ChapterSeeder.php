<?php

namespace Database\Seeders;

use App\Models\Chapter;
use App\Models\Work;
use Illuminate\Database\Seeder;

class ChapterSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
         Chapter::factory(25)
             ->recycle(Work::all())
             //->has(Work::factory()->count(fake()->numberBetween(1, 5)))
             ->create();
    }
}
