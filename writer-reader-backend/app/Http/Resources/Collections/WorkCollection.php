<?php

namespace App\Http\Resources\Collections;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\ResourceCollection;

class WorkCollection extends ResourceCollection
{
    /**
     * Transform the resource collection into an array.
     *
     * @return array<int|string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'data' => $this->collection->transform( function($work) {
                return[
                    'id' => $work->id,
                    'title' => $work->title,
                    'creator_id' => $work->user_id,
                    'creator_name' => $work->creator->name,
                    'created_at' => $work->created_at,
                    'updated_at' => $work->updated_at,
                    'language' => $work->language->name,
                    'category' => $work->categories->pluck('name'),
                    'likes' => $work->likes->count(),
                ];
            })
        ];
    }
}
