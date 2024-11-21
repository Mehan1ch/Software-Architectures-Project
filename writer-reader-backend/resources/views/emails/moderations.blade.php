<x-mail::message>
    Hello {{$creator->name}},

    Your Work No.{{$work->id}} has been arrived successfully and is now being processed.

<x-mail::button :url="$work_url">
View Work
</x-mail::button>

Thanks,<br>
{{ config('app.name') }}
</x-mail::message>
