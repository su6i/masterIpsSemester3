import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Comment } from '../comment.model';
import { CommentService } from '../comment.service';

@Component({
  selector: 'app-comment-form-list',
  templateUrl: './comment-form-list.component.html',
  styleUrls: ['./comment-form-list.component.css']
})
export class CommentFormListComponent implements OnInit, OnDestroy {

  comments: Comment[] = [];
  private commentSubscriprion: Subscription;

  constructor(public commentService: CommentService) { }

  ngOnInit(): void {
    this.comments = this.commentService.getComments();
    this.commentSubscriprion = this.commentService.getCommentUpdateListener().subscribe((comments: Comment[]) => {
      this.comments = comments;

    });
}
ngOnDestroy(){
  this.commentSubscriprion.unsubscribe();
}
}
