<?php

namespace Database\Factories;

use App\Enums\ModerationEnum;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Work>
 */
class WorkFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'content' => $this->faker->text,
            'creator_id' => User::all()->random()->id,
            'moderation_status' => $this->faker->randomElement(ModerationEnum::class->toArray()),
            'moderator_id' => $this->faker->randomNumber(),
            'rating_id' => $this->faker->randomNumber(),
            'language_id' => $this->faker->randomNumber(),
            'created_at' => $this->faker->dateTime(),
            'updated_at' => $this->faker->dateTime(),
        ];
    }
}
