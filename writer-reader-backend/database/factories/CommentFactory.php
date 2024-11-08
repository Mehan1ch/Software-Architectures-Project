<?php

namespace Database\Factories;

use App\Models\Collection;
use App\Models\Comment;
use App\Models\User;
use App\Models\Work;
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
        $commentable = $this->faker->randomElement([Work::all()->random(), Collection::all()->random()]);
        return [
            'content' => $this->faker->text(),
            'user_id' => User::all()->random()->id,
            'commentable_id' => $commentable->id,
            'commentable_type' => $commentable->getMorphClass(),
        ];
    }
}
