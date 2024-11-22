<x-mail::message>
    Hello {{$creator->name}},

    Your {{ $work->title }}
    @if ($work->moderation_status === App\Enums\ModerationEnum::APPROVED)
        has been accepted by the moderator and can be viewed publicly on our website or mobile app.
        Thank you and we look forward to your next work!
    @elseif ($work->moderation_status === App\Enums\ModerationEnum::EDIT_REQUESTED)
        has been declined by the moderator, please modify your work so that it will be accepted the next time.
        Thank you for your understanding and we look forward to your next submission.
    @else
        Error!
    @endif

<x-mail::button :url="url('/api/works/' . $work->id)">
View Work
</x-mail::button>

Thanks,<br>
{{ config('app.name') }}
</x-mail::message>
