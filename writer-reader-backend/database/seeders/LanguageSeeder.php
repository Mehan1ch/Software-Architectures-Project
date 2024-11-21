<?php

namespace Database\Seeders;

use App\Models\Language;
use App\Models\Work;
use Illuminate\Database\Seeder;

class LanguageSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Language::factory(25)->create();
    }
}
