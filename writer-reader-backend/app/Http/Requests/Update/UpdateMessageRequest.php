<?php

namespace App\Http\Requests\Update;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class UpdateMessageRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, ValidationRule|array|string>
     */
    public function rules(): array
    {
        return [
            "content" => "required|string",
            "sent_by_id" => "required|uuid|exists:users,id",
            "sent_to_id" => "required|uuid|exists:users,id",
        ];
    }
}