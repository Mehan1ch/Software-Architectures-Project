<?php

namespace App\Http\Resources\Collections;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\ResourceCollection;

class CollectionCollection extends ResourceCollection
{
    /**
     * Transform the resource collection into an array.
     *
     * @return array<int|string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'data' => $this->collection->transform(function ($collection) {
                return [
                    'id' => $collection->id,
                    'name' => $collection->name,
                    'description' => $collection->description,
                    'creator_id' => $collection->user_id,
                    'creator_name' => $collection->user->name,
                    'number_of_works' => $collection->works->count(),
                    'likes' => $collection->likes->count(),
                    'created_at' => $collection->created_at,
                    'updated_at' => $collection->updated_at,
                ];
            })
        ];
    }
}
