<?php

namespace Database\Factories;

use App\Models\Collection;
use App\Models\Comment;
use App\Models\Like;
use App\Models\User;
use App\Models\Work;
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
        $likeable = $this->faker->randomElement([Work::all()->random(), Collection::all()->random(), Comment::all()->random()]);
        return [
            'user_id' => User::all()->random()->id,
            'likeable_id' => $likeable->id,
            'likeable_type' => $likeable->getMorphClass(),
        ];
    }
}
