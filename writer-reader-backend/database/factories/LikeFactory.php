<?php

namespace Database\Factories;

use App\Models\Like;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Like>
 */
class LikeFactory extends Factory
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
            'usr_id' => User::all()->random()->id,
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
            //
        ];
    }
}
