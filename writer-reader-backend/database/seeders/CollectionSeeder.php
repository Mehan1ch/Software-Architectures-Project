<?php

namespace Database\Seeders;

use App\Models\Collection;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Seeder;

class CollectionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Collection::factory(20)
            ->has(Work::factory()->count(fake()->numberBetween(1,10)))
            ->recycle(User::all())
            //->for(User::factory())
            //->has(Work::factory()->count(fake()->numberBetween(1, 10)))
            ->create();
    }
}
