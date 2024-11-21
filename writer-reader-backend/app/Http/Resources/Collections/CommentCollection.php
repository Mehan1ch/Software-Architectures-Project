<?php

namespace App\Http\Resources\Collections;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\ResourceCollection;

class CommentCollection extends ResourceCollection
{
    /**
     * Transform the resource collection into an array.
     *
     * @return array<int|string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'data' => $this->collection->transform(function ($comment){
                return [
                    'id' => $comment->id,
                    'content' => $comment->content,
                    'user_id' => $comment->user->id,
                    'user_name' => $comment->user->name,
                    'created_at' => $comment->created_at,
                    'updated_at' => $comment->updated_at,
                    'commentable_id' => $comment->commentable_id,
                    'commentable_type' => $comment->commentable_type,
                    'likes' => $comment->likes->count(),
                    'is_liked' => $comment->likes->contains('user_id', auth()->id()),
                ];
            })
        ];
    }
}
