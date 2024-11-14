<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class MessageResource extends JsonResource
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
            'content' => $this->content,
            'sent_by' => UserResource::make($this->sent_by),
            'sent_to' => UserResource::make($this->sent_to),
            'created_at' => $this->created_at,
            'updated_at' => $this->updated_at,
        ];
    }
}
