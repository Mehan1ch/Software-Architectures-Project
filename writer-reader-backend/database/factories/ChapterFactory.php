<?php

namespace Database\Factories;

use App\Models\Chapter;
use App\Models\Work;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Chapter>
 */
class ChapterFactory extends Factory
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
            'title' => $this->faker->sentence,
            'content' => $this->faker->paragraphs(3, true),
            'work_id' => Work::all()->random()->id,
            'created_at' => $created_at,
            'updated_at' => $this->faker->dateTimeBetween($created_at),
            //
        ];
    }
}
