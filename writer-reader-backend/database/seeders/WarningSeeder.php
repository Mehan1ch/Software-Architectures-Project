<?php

namespace Database\Seeders;

use App\Models\Warning;
use App\Models\Work;
use Illuminate\Database\Seeder;

class WarningSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Warning::factory(25)
            ->recycle(Work::all())
            //->has(Work::factory())
            ->create();
    }
}
