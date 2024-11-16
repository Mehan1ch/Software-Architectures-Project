<?php

namespace App\Http\Resources;

use App\Http\Resources\Collections\WorkCollection;
use App\Models\Rating;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

/**
 * Class TagResource
 * @package App\Http\Resources
 * @mixin Rating
 */
class RatingResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'id' => $this->id,
            'details' => $this->details,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
            'works' => WorkCollection::make($this->works),
        ];
    }
}
