<?php

namespace Database\Factories;

use App\Models\Warning;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Warning>
 */
class WarningFactory extends Factory
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
            'details' => $this->faker->sentence(),
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
        ];
    }
}
