<?php

namespace Database\Seeders;

use App\Models\Collection;
use App\Models\Message;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Seeder;

class MessageSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Message::factory(25)
            ->recycle(User::all())
            ->create();
        Message::factory(25)
            ->recycle(User::all())
            ->create();
    }
}
