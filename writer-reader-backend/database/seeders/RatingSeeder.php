<?php

namespace Database\Seeders;

use App\Models\Rating;
use App\Models\Work;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class RatingSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Rating::factory(25)
            ->recycle(Work::all())
            //->has(Work::factory())
            ->create();
    }
}
