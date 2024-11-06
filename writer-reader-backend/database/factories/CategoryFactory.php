<?php

namespace Database\Factories;

use App\Models\Category;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Category>
 */
class CategoryFactory extends Factory
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
            'name' => $this->faker->word,
            'description' => $this->faker->sentence,
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
        ];
    }
}
