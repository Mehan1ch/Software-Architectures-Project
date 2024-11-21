<?php

namespace Database\Seeders;

use App\Models\Category;
use App\Models\Work;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class CategorySeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Category::factory(25)
            ->recycle(Work::all())
            //->has(Work::factory()->count(fake()->numberBetween(1, 5)))
            ->create();
    }
}
