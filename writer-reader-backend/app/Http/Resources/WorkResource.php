<?php

namespace App\Http\Resources;

use App\Http\Resources\Collections\CategoryCollection;
use App\Http\Resources\Collections\ChapterCollection;
use App\Http\Resources\Collections\CharacterCollection;
use App\Http\Resources\Collections\CommentCollection;
use App\Http\Resources\Collections\LikeCollection;
use App\Http\Resources\Collections\TagCollection;
use App\Http\Resources\Collections\WarningCollection;
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
            'creator_id' => $this->creator->id,
            'creator_name' => $this->creator->name,
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
            'moderation_status' => $this->moderation_status,
            'moderator_id' => $this->moderator_id,
            'moderator_name' => $this->moderator->name,
            'language' => $this->language->name,
            'rating' => $this->rating->details,
            'warnings' => $this->warnings->pluck('details'),
            'characters' => $this->characters->pluck('name'),
            'tags' => $this->tags->pluck('name'),
            'categories' => $this->categories->pluck('name'),
            'chapters' => ChapterResource::collection($this->chapters),
            'content' => $this->content,
            'likes' => $this->likes->count(),
            'is_liked' => $this->likes->contains('user_id', auth()->id()),
            'comments' => CommentResource::collection($this->comments),
        ];
    }
}
