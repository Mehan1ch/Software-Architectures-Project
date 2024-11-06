<?php

namespace Database\Factories;

use App\Models\Message;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Message>
 */
class MessageFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $created_at = $this->faker->dateTime();
        return [
            'content' => $this->faker->text,
            'sent_by_id' => User::all()->random()->id,
            'sent_to_id' => User::all()->random()->id,
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
        ];
    }
}
