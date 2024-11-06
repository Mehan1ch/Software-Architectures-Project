<?php

namespace Database\Factories;

use App\Models\Comment;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Comment>
 */
class CommentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $createdAt = $this->faker->dateTime();
        return [
            'content' => $this->faker->text(),
            'user_id' => User::all()->random()->id,
            'created_at' => $createdAt,
            'updated_at' => $this->faker->dateTimeBetween($createdAt),
        ];
    }
}
