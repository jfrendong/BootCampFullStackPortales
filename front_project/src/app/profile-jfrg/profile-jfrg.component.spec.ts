import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileJfrgComponent } from './profile-jfrg.component';

describe('ProfileJfrgComponent', () => {
  let component: ProfileJfrgComponent;
  let fixture: ComponentFixture<ProfileJfrgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileJfrgComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileJfrgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
