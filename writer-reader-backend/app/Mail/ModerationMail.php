<?php

namespace App\Mail;

use App\Models\Work;
use App\Models\User;
use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Mailable;
use Illuminate\Mail\Mailables\Content;
use Illuminate\Mail\Mailables\Envelope;
use Illuminate\Queue\SerializesModels;
use Illuminate\Support\Facades\URL;

class ModerationMail extends Mailable
{
    use Queueable, SerializesModels;

    public $work, $user;

    /**
     * Create a new message instance.
     */
    public function __construct(Work $work, User $user)
    {
        $this->work = $work;
        $this->user = $user;
    }

    /**
     * Get the message envelope.
     */
    public function envelope(): Envelope
    {
        return new Envelope(
            subject: 'Moderation Mail working on ' . $this->work->title,
        );
    }

    /**
     * Get the message content definition.
     */
    public function content(): Content
    {
        return new Content(
            markdown: 'emails.moderations',
            with:[
                'work' => $this->work,
                'creator' => $this->user,
                'work_url' => URL::route('works.show', $this->work->id)
            ]
        );
    }

    /**
     * Get the attachments for the message.
     *
     * @return array<int, \Illuminate\Mail\Mailables\Attachment>
     */
    public function attachments(): array
    {
        return [];
    }
}
