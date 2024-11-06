<?php

namespace Database\Factories;

use App\Enums\ModerationEnum;
use App\Models\Language;
use App\Models\Rating;
use App\Models\User;
use App\Models\Work;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Work>
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
        $created_at = $this->faker->dateTime();
        return [
            'name' => $this->faker->sentence,
            'content' => $this->faker->paragraphs(3, true),
            'creator_id' => User::all()->random()->id,
            'moderation_status' => $this->faker->randomElement(ModerationEnum::values()),
            'moderator_id' => User::all()->random()->id,
            'rating_id' => Rating::all()->random()->id,
            'language_id' => Language::all()->random()->id,
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
        ];
    }
}
