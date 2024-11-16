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
            'content' => $this->content,
            'creator' => UserResource::make($this->creator),
            'moderation_status' => $this->moderation_status,
            'moderator' => UserResource::make($this->moderator),
            'chapters' => ChapterCollection::make($this->chapters),
            'rating' => RatingResource::make($this->rating),
            'language' => LanguageResource::make($this->language),
            'warnings' => WarningCollection::make($this->warnings),
            'characters' => CharacterCollection::make($this->characters),
            'tags' => TagCollection::make($this->tags),
            'categories' => CategoryCollection::make($this->categories),
            'likes' => LikeCollection::make($this->likes),
            'comments' => CommentCollection::make($this->comments),
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
