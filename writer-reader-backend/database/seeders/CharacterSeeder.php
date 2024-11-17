<?php

namespace Database\Seeders;

use App\Models\Character;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Seeder;

class CharacterSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Character::factory(25)
            ->recycle(Work::all())
            ->recycle(User::all())
            //->has(Work::factory()->count(fake()->numberBetween(1,10)))
            ->create();
    }
}
