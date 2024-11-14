<?php

namespace App\Http\Resources;

use App\Models\Work;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

/**
 * Class WorkResource
 * @package App\Http\Resources
 * @mixin Work
 */
class WorkResource extends JsonResource
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
            'title' => $this->title,
            'content' => $this->content,
            'creator' => UserResource::make($this->creator),
            'moderation_status' => $this->moderation_status,
            'moderator' => UserResource::make($this->moderator),
            'chapters' => ChapterResource::collection($this->chapters),
            'rating' => RatingResource::make($this->rating),
            'language' => LanguageResource::make($this->language),
            'warnings' => WarningResource::collection($this->warnings),
            'characters' => CharacterResource::collection($this->characters),
            'tags' => TagResource::collection($this->tags),
            'categories' => CategoryResource::collection($this->categories),
            'likes' => LikeResource::collection($this->likes),
            'comments' => CommentResource::collection($this->comments),
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
